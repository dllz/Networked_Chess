package server.networking;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Daniel on 2016/11/02.
 * This makes it a lot easier to keep track of tcp connections
 */
public class WhiteNetworkHandler extends GameNetworkHandler {
    public WhiteNetworkHandler(Socket ConnectionToClient) throws IOException {
        super(ConnectionToClient, "WHITE");
    }

}
