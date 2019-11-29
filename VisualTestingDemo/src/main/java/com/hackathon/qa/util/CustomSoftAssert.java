package com.hackathon.qa.util;

import java.util.Map;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;


public class CustomSoftAssert extends Assertion {
	
private final Map<AssertionError ,IAssert<?>> m_errors = Maps.newLinkedHashMap();

private String assertMessage = null;


@Override
protected void doAssert(IAssert<?> a) {
    onBeforeAssert(a);
    try {
      assertMessage = a.getMessage();
      a.doAssert();
      onAssertSuccess(a);
    } catch(AssertionError ex) {
      onAssertFailure(a, ex);
      m_errors.put(ex, a);    
      TestUtil.captureScreenshot();
    } finally {
      onAfterAssert(a);
    }
  }

public void assertAll() {
    if (!m_errors.isEmpty()) {
        StringBuilder sb = new StringBuilder("The following asserts failed:");
        boolean first = true;
        for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
            if (first) {
                first = false;
            } else {
                sb.append(",");
            }
            sb.append("\n\t");
            sb.append(ae.getKey().getMessage());
        }
        throw new AssertionError(sb.toString());
    }
}

}
