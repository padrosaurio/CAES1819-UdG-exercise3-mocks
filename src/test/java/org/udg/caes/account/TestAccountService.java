package org.udg.caes.account;

import com.google.inject.Inject;
import mockit.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountService {

  Account acc1 = new Account("juan",200);
  Account acc2 = new Account("pedro",400);

  @Test
  void testTransfer(@Tested AccountService as, @Mocked AccountManager am)  {

    new Expectations(){{
      am.findAccount("juan"); result = acc1;
      am.findAccount("pedro"); result = acc2;
    }};

    as.setAccountManager(am);
    as.transfer("juan","pedro",100);

    assertEquals(acc1.getBalance(),100);
    assertEquals(acc2.getBalance(),500);

    new Verifications(){{
      am.updateAccount(acc1); times = 1;
      am.updateAccount(acc2); times = 1;
    }};

  }
}