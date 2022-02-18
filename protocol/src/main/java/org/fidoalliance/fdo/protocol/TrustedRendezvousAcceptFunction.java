package org.fidoalliance.fdo.protocol;

import java.io.IOException;
import org.fidoalliance.fdo.protocol.dispatch.RendezvousAcceptFunction;
import org.fidoalliance.fdo.protocol.message.To0OwnerSign;

public class TrustedRendezvousAcceptFunction implements RendezvousAcceptFunction {

  @Override
  public Boolean apply(To0OwnerSign to0OwnerSign) throws IOException {
    return true;
  }
}
