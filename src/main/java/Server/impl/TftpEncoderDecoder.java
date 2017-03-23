package Server.impl;

import Server.api.MessageEncoderDecoder;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Created by Mika on 10/01/2017.
 */
public class TftpEncoderDecoder implements MessageEncoderDecoder {

    final int NUM_OF_BYTES = 520;
    ByteBuffer buf = ByteBuffer.allocate(NUM_OF_BYTES);
    private int len = 0;
    private short opCode;
    private int sizeDataPacket;


    @Override
    public Packet decodeNextByte(byte nextByte) {
        boolean push = false;
        if(len<2){
            pushByte(nextByte);
            push = true;
        }

        if(len>=2){
            if(len==2){
         
                byte[] byteOpCode =new byte[2];
                byteOpCode[0]=buf.get(0);
                byteOpCode[1] = buf.get(1);
                opCode = bytesToShort(byteOpCode);
            }
            switch (opCode){
                case(short)0:{
                    len = 0;
                    buf.clear();
                    return null;
                }
                case (short) 3:{
                    if(len<4&&!push){
                        pushByte(nextByte);
                        push = true;
                    }
                    if (len == 4) {
                        byte[] byteSizeDataPacket = {buf.get(2),buf.get(3)};
                        sizeDataPacket =  (bytesToShort(byteSizeDataPacket))+6;
                    }
                    if(len>=4){
                       
                        if (len < sizeDataPacket&&!push){
                            pushByte(nextByte);
                            push = true;
                        }
                        if (len == sizeDataPacket){
                           return popPacket();
                        }
                    }
                    break;
                }
                case (short) 4:{
                    if (len<4&&!push){
                        pushByte(nextByte);
                        push = true;
                    }
                    if(len==4){
                        return popPacket();
                    }
                    break;
                }
                case (short) 5:{
                    if(len<4&&!push){
                        pushByte(nextByte);
                        push = true;
                    }
                    if(len>=4){
                        if (!push){
                            pushByte(nextByte);
                            push = true;
                        }
                    }
                    if(len>4&&nextByte=='\0') return popPacket();
                    break;
                }
                case (short) 6:{
                    return popPacket();
                }
                case (short) 10:{
                    return popPacket();
                }
                default:{
                    if (nextByte == '\0') {
                        return popPacket();
                    }
                    else {
                        if(len>=2&&!push){pushByte(nextByte);}
                    }
                    break;
                }
            }


         }
         return null;
    }


    private Packet popPacket() {
        Packet packetAns=null;
        buf.flip();
        buf.getShort();
      

        switch (opCode){
            case (short) 1: {
                String fileName = new String(buf.array(),2,len-2,StandardCharsets.UTF_8);
                packetAns = new Rrq(fileName);
                len = 0;
                buf.clear();
                return packetAns;
            }
            case (short) 2:{
                String fileName = new String(buf.array(),2,len-2,StandardCharsets.UTF_8);
                packetAns = new Wrq(fileName);
                len = 0;
                buf.clear();
                return packetAns;
            }
            case (short) 3: {
                short packetSize = buf.getShort();
                short block = buf.getShort();
                byte[] bufArray = buf.array();
                byte[] data = new byte[packetSize];
                int index =0;
                for (int i=buf.position(); i < len ; i++){
                    data[index++] = bufArray[i];
                }
                packetAns = new Data(block,packetSize,data);
                len = 0;
                buf.clear();
                return packetAns;
            }
            case (short) 4:{
                short block= buf.getShort();
                packetAns = new Ack(block);
                len = 0;
                buf.clear();
                return packetAns;
            }
            case (short) 5:{
                short errorCode = buf.getShort();
                String msg = new String(buf.array(),4,len-4,StandardCharsets.UTF_8);
                packetAns = new Error(errorCode,msg);
                len = 0;
                buf.clear();
                return packetAns;
            }
            case (short) 6:{
                packetAns = new Dirq();
                len = 0;
                buf.clear();
                return packetAns;
            }
            case (short) 7:{
                String userName = new String(buf.array(),2,len-2,StandardCharsets.UTF_8);
                packetAns = new Logrq(userName);
                len = 0;
                buf.clear();
                return packetAns;
            }
            case (short) 8:{
                String fileName = new String(buf.array(),2,len-2,StandardCharsets.UTF_8);
                packetAns = new Delrq(fileName);
                len = 0;
                buf.clear();
                return packetAns;
            }
            case (short) 9:{
                byte added = buf.get();
                String fileName = new String(buf.array(),3,len-3,StandardCharsets.UTF_8);
                packetAns = new Bcast(added,fileName);
                len = 0;
                buf.clear();
                return packetAns;
            }
            case (short) 10:{
                packetAns = new Disc();
                len = 0;
                buf.clear();
                return packetAns;
            }
        }

        len = 0;
        buf.clear();
        return packetAns;
    }

    private void pushByte(byte nextByte) {
        buf.put(nextByte);
        len++;
    }

    @Override
    public byte[] encode(Object message) {
        short opCode = ((Packet)message).getOpCode();
        byte[] ans ;
        switch (opCode){
            case (short) 1: {
                byte[] temp= (((Rrq)message).getFileName() ).getBytes();
                ans = new byte[temp.length+3];
                ans[0] = shortToBytes(opCode)[0];
                ans[1] = shortToBytes(opCode)[1];
                for (int i=0;i<temp.length;i++){
                    ans[i+2] = temp[i];
                }
                ans[ans.length-1] = '\0';
                return ans;
            }
            case (short) 2:{
                byte[] temp= (((Wrq)message).getFileName() ).getBytes();
                ans = new byte[temp.length+3];
                ans[0] = shortToBytes(opCode)[0];
                ans[1] = shortToBytes(opCode)[1];
                for (int i=0;i<temp.length;i++){
                    ans[i+2] = temp[i];
                }
                ans[ans.length-1] = '\0';
                return ans;

            }
            case (short) 3:{
                short dataSize = ((Data)message).getPacketSize();
                ans = new byte[6+dataSize];
                ans[0] = shortToBytes(opCode)[0];
                ans[1] = shortToBytes(opCode)[1];
                ans[2] = shortToBytes(dataSize)[0];
                ans[3] = shortToBytes(dataSize)[1];;
                ans[4] = shortToBytes(((Data) message).getBlock())[0];
                ans[5] = shortToBytes(((Data) message).getBlock())[1];
                byte[] temp = ((Data) message).getData();
                for (int i=0;i<temp.length;i++){
                    ans[i+6] = temp[i];
                }
                return ans;
            }
            case (short) 4:{
                ans = new byte[4];
                ans[0] = shortToBytes(opCode)[0];
                ans[1] = shortToBytes(opCode)[1];
                ans[2] = shortToBytes(((Ack) message).getBlock())[0];
                ans[3] = shortToBytes(((Ack) message).getBlock())[1];;
                return ans;
            }
            case (short) 5: {
                byte[] temp= (((Error)message).getErrMsg()).getBytes();
                ans = new byte[5+temp.length];
                ans[0] = shortToBytes(opCode)[0];
                ans[1] = shortToBytes(opCode)[1];
                ans[2] = shortToBytes(((Error) message).getErrorCode())[0];
                ans[3] = shortToBytes(((Error) message).getErrorCode())[1];
                for (int i=0;i<temp.length;i++){
                    ans[i+4] = temp[i];
                }
                ans[ans.length-1] = '\0';
                return ans;
            }
            case (short) 6: {
                ans = new byte[2];
                ans[0] = shortToBytes(opCode)[0];
                ans[1] = shortToBytes(opCode)[1];
                return ans;
            }
            case (short) 7: {
                byte[] temp= (((Logrq)message).getUsername()).getBytes();
                ans = new byte[temp.length+3];
                ans[0] = shortToBytes(opCode)[0];
                ans[1] = shortToBytes(opCode)[1];
                for (int i=0;i<temp.length;i++){
                    ans[i+2] = temp[i];
                }
                ans[ans.length-1] = '\0';
                return ans;
            }
            case (short) 8: {
                byte[] temp= (((Delrq)message).getFileName() ).getBytes();
                ans = new byte[temp.length+3];
                byte[] opcodeByteArray = shortToBytes(opCode);
                ans[0] = opcodeByteArray[0];
                ans[1] = opcodeByteArray[1];
                for (int i=0;i<temp.length;i++){
                    ans[i+2] = temp[i];
                }
                ans[ans.length-1] = '\0';
                return ans;
            }
            case (short) 9:{
                byte[] temp= (((Bcast)message).getFileName()).getBytes();
                ans = new byte[temp.length+4];
                byte[] opcodeByteArray = shortToBytes(opCode);
                ans[0] = opcodeByteArray[0];
                ans[1] = opcodeByteArray[1];
                ans[2] = ((Bcast) message).getDel_add();
                for (int i=0;i<temp.length;i++){
                    ans[i+3] = temp[i];
                }
                ans[ans.length-1] = '\0';
                return ans;
            }
            case  (short) 10: {
                ans = new byte[1];
                ans[0] = shortToBytes(opCode)[0];
                ans[1] = shortToBytes(opCode)[1];
                return ans;
            }

        }
        return new byte[0];
    }

    public byte[] shortToBytes(short num)
    {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }

    public short bytesToShort(byte[] byteArr)
    {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }
}
