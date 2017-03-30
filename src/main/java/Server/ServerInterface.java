/*
package Server;

import Client.RequestOrganization.OrderInstruction;
import Server.srv.NetworkImplementation.Printer;

*/
/**
 * Created by nimrod on 30-Mar-17.
 *//*

public class ServerInterface
{
    private Printer printer;

    public ServerInterface(Printer printer) {
        this.printer = printer;
    }

    */
/**
     * print the order
     * @param jsonName the name of the json file
     *//*

    public void printOrder(String jsonName)
    {
        OrderInstruction order = createOrder(jsonName);
        UnZip.unZipIt(order);
        printer.sendOrderInstruction(order);
    }

    */
/**
     * @param jsonName
     * @return Order Instruction from the json file
     *//*

    public OrderInstruction createOrder(String  jsonName)
    {
        OrderInstruction order = FromJson.fromJson(jsonName);
        return order;
    }
}
*/
