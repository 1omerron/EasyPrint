package Client.API.Packets;

import Client.RequestOrganization.OrderInstruction;

/**
 * Created by 1omer on 26/03/2017.
 */
public class OrderPacket extends Packet
{
    private OrderInstruction order;
    // TODO add all the field instead of the order object

    public OrderPacket(char code, char operation, OrderInstruction order)
    {
        super(code,operation,"".getBytes() /* TODO change the empty string for the super constructor! */);
        this.order = order;
    }
}
