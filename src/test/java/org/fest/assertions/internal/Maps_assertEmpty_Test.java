/*
 * Created on Dec 21, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static java.util.Collections.emptyMap;
import static org.fest.assertions.data.MapEntry.entry;
import static org.fest.assertions.error.ShouldBeEmpty.shouldBeEmpty;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.MapFactory.map;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link Maps#assertEmpty(AssertionInfo, Map)}</code>.
 * 
 * @author Alex Ruiz
 */
public class Maps_assertEmpty_Test {

  @Rule
  public ExpectedException thrown = none();

  private Failures failures;
  private Maps maps;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    maps = new Maps();
    maps.failures = failures;
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    maps.assertEmpty(someInfo(), emptyMap());
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    maps.assertEmpty(someInfo(), null);
  }

  @Test
  public void should_fail_if_actual_has_elements() {
    AssertionInfo info = someInfo();
    Map<?, ?> actual = map(entry("name", "Yoda"));
    try {
      maps.assertEmpty(info, actual);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeEmpty(actual));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
}
