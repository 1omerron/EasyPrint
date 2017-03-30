package Server;

import Client.RequestOrganization.OrderInstruction;

/**
 * Created by nimrod on 30/03/2017.
 */
public class PrintOrder
{
    public OrderInstruction createOrder(String  jsonName)
    {
        OrderInstruction order = FromJson.fromJson(jsonName);
        return order;
    }
}
