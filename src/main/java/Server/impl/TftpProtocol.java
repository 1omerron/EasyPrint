package Server.impl;

import Server.api.bidi.BidiMessagingProtocol;
import Server.api.bidi.Connections;
import Server.srv.ConnectionHandler;
import Server.srv.ConnectionsImpl;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.Lock;

/**
 * Created by Mika on 09/01/2017.
 */
public class TftpProtocol<T> implements BidiMessagingProtocol<T> {

    private int connectionId;
    private Connections connections;
    private boolean logIn = false;
    private static ConcurrentHashMap<String, ConnectionHandler> logInList = new ConcurrentHashMap<>();
    private short ackCounter = 1;
    private byte[] dataBlock = new byte[512];
    boolean helpAckCounter = false;
    private FileInputStream InFile;
    private boolean shouldTerminate = false;
    private String userName;
    private FileOutputStream outFile;
    private boolean wrqSent = false;
    private String uploadfileName;
    private File fileBeingUpLoad;
    private boolean dirqBeingSent = false;
    private ConcurrentLinkedQueue dataPacketQueue = new ConcurrentLinkedQueue();
    private static Object lock = new Object();


    @Override
    public void start(int connectionId, Connections connections) {
        this.connectionId = connectionId;
        this.connections = connections;
    }

    @Override
    public void process(Object message) throws IOException {
        short opCode = ((Packet) message).getOpCode();
        switch (opCode) {

            case (short) 1: {
                if (logIn) {
                    synchronized (lock){
                        File filetemp = new File("Files" + File.separator + ((Rrq) message).getFileName());
                        if (filetemp.exists()) {
                            if (filetemp.canRead()) {
                                InFile = new FileInputStream("Files" + File.separator + ((Rrq) message).getFileName());
                                int i = InFile.read(dataBlock);
                                Data response = new Data(ackCounter, (short) i, dataBlock);
                                connections.send(connectionId, response);
                                if (i < 512) {
                                    InFile.close();
                                    helpAckCounter = false;
                                    ackCounter = 1;
                                } else {
                                    ackCounter++;
                                    helpAckCounter = true;
                                }
                            } else {
                                Server.impl.Error response = new Error((short) 2, "Access violation – File cannot be written, read or deleted.");
                                connections.send(connectionId, response);
                            }
                        } else {
                            Error response = new Error((short) 1, "File not found – RRQ \\ DELRQ of non-existing file");
                            connections.send(connectionId, response);
                        }
                    }

                } else {
                    Error response = new Error((short) 6, "User not logged in – Any opcode received before Login completes.");
                    connections.send(connectionId, response);
                }
                break;
            }

            case (short) 2: {
                if (logIn) {
                    synchronized (lock) {
                        File filetemp = new File("Files" + File.separator + ((Wrq) message).getFileName());
                        uploadfileName = ((Wrq) message).getFileName();
                        if (!filetemp.exists()) {
                            fileBeingUpLoad = filetemp;
                            wrqSent = true;
                            filetemp.setReadable(false);
                            filetemp.createNewFile();
                            filetemp.setReadable(false);
                            outFile = new FileOutputStream("Files" + File.separator + ((Wrq) message).getFileName());
                            Ack response = new Ack((short) 0);
                            connections.send(connectionId, response);
                        } else {
                            Error response = new Error((short) 5, "File already exists – File name exists on WRQ.");
                            connections.send(connectionId, response);
                        }
                    }
                } else {
                    Error response = new Error((short) 6, "User not logged in – Any opcode received before Login completes.");
                    connections.send(connectionId, response);
                }
                break;
            }
            case (short) 3: {
                if (logIn) {
                    if (wrqSent) {
                        outFile.write(((Data) message).getData());
                        outFile.flush();
                        Ack response = new Ack(((Data) message).getBlock());
                        connections.send(connectionId, response);
                        if (((Data) message).getPacketSize() < 512) {
                            outFile.close();
                            wrqSent = false;
                            byte b = (byte) 1;
                            Bcast broadcast = new Bcast(b, uploadfileName);
                        
                            for(ConnectionHandler h : logInList.values()) {
                                connections.send(h.getId(),broadcast);
                            }
                            fileBeingUpLoad.setReadable(true);

                        }
                    }
                    else {
                        Error response = new Error((short) 0, "Not defined, see error message (if any).");
                        connections.send(connectionId, response);
                    }
                } else {
                    Error response = new Error((short) 6, "User not logged in – Any opcode received before Login completes.");
                    connections.send(connectionId, response);
                }
                break;
            }
            case (short) 4: {
                if (logIn) {

                    if (helpAckCounter && ((Ack) message).getBlock() == ackCounter - 1) {
                        int i = InFile.read(dataBlock);
                        Data response = new Data(ackCounter, (short) i, dataBlock);
                        connections.send(connectionId, response);
                        if (i < 512) {
                            InFile.close();
                            helpAckCounter = false;
                            ackCounter = 1;
                        } else {
                            ackCounter++;
                            helpAckCounter = true;
                        }
                    }
                    if (dirqBeingSent && ((Ack) message).getBlock() == ackCounter) {
                        connections.send(connectionId, dataPacketQueue.poll());
                        if (dataPacketQueue.isEmpty()) {
                            dirqBeingSent = false;
                            ackCounter = 1;
                        } else ackCounter++;
                    }

                } else {
                    Error response = new Error((short) 6, "User not logged in – Any opcode received before Login completes.");
                    connections.send(connectionId, response);
                }

                break;
            }
            case (short) 5: {
                if (logIn) {
                   
                    if (wrqSent) {
                        fileBeingUpLoad.delete();
                        wrqSent = false;
                    }
                } else {
                    Error response = new Error((short) 6, "User not logged in – Any opcode received before Login completes.");
                    connections.send(connectionId, response);
                }
                break;
            }
            case (short) 6: {
                if (logIn) {
                    File folder = new File("Files");
                    File[] listOfFiles=folder.listFiles();
                    String dirList="";
                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].canRead()) {
                            dirList = dirList+listOfFiles[i].getName()+'\0';
                        }
                    }
               
                    byte[] dataByte = dirList.getBytes();
                    createDataPackets(dataByte);
                    connections.send(connectionId, dataPacketQueue.poll());
                    if (!dataPacketQueue.isEmpty()) {
                        dirqBeingSent = true;
                    }


                } else {
                    Error response = new Error((short) 6, "User not logged in – Any opcode received before Login completes.");
                    connections.send(connectionId, response);
                }
                break;
            }
            case (short) 7: {
                if (!logInList.containsKey(((Logrq) message).getUsername())) {
                    ConnectionHandler handler = ((ConnectionsImpl<Packet>) connections).getConnectedClients().get(new Integer(this.connectionId));
                    if(logInList.contains(handler)){
                        Error response = new Error((short) 7, "User already logged in – Login username already connected.");
                        connections.send(connectionId, response);
                    }
                    else {
                        logInList.put(((Logrq) message).getUsername(), handler);
                        userName = ((Logrq) message).getUsername();
                        Ack response = new Ack((short) 0);
                        connections.send(connectionId, response);
                        logIn = true;
                    }
                } else {
                    Error response = new Error((short) 7, "User already logged in – Login username already connected.");
                    connections.send(connectionId, response);
                }
                break;
            }
            case (short) 8: {
                if (logIn) {
                    File file = new File("Files" + File.separator + ((Delrq) message).getFileName());
                    if (file.exists()) {
                        file.delete();
                        Ack response = new Ack((short) 0);
                        byte b = 0;
                        Bcast broadcast = new Bcast(b, ((Delrq) message).getFileName());
                        connections.send(connectionId, response);
                        Collection<ConnectionHandler> handlers = logInList.values();
                        connections.sendAll(handlers,broadcast);
                    } else {
                        Error response = new Error((short) 1, "File not found – RRQ \\ DELRQ of non-existing file");
                        connections.send(connectionId, response);
                    }
                } else {
                    Error response = new Error((short) 6, "User not logged in – Any opcode received before Login completes.");
                    connections.send(connectionId, response);
                }
                break;
            }

            case (short) 10: {
                if (logIn) {
                    logInList.remove(userName);
                    Ack response = new Ack((short) 0);
                    connections.send(connectionId, response);
                    connections.disconnect(connectionId);
                    shouldTerminate = true;
                } else {
                    Error response = new Error((short) 6, "User not logged in – Any opcode received before Login completes.");
                    connections.send(connectionId, response);
                }
                break;
            }
            default: {
                Error response = new Error((short) 4, "Illegal TFTP operation – Unknown Opcode.");
                connections.send(connectionId, response);
                break;
            }

        }


    }

    private void sendBcast(Bcast msg) throws IOException {
        for (ConnectionHandler h:logInList.values()) {
            connections.send(h.getId(),msg);

        }
    }
    private void createDataPackets(byte[] data) {
        ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.put(data);
        buffer.flip();
        int numOfBlocks = data.length / 512;
        int minBlockSize = data.length % 512;
        if (minBlockSize != 0) {
            numOfBlocks++;
        }
        if (numOfBlocks == 0) {
            byte[] ans = new byte[0];
            Data dataPacket = new Data((short) 1, (short) 0, ans);
            dataPacketQueue.add(dataPacket);
        } else {
            for (int i = 1; i <= numOfBlocks; i++) {
                byte[] ans;
                short packetSize;
                if (i != numOfBlocks) {
                    ans = new byte[512];
                    packetSize = 512;
                } else {
                    ans = new byte[minBlockSize];
                    packetSize = (short) minBlockSize;
                }
                buffer.get(ans, 0, packetSize);
                Data dataPacket = new Data((short) i, packetSize, ans);
                dataPacketQueue.add(dataPacket);
            }
        }
        buffer.clear();
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
