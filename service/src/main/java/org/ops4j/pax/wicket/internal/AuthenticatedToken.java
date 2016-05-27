/**
 * Copyright OPS4J
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.wicket.internal;

import java.io.Serializable;
import java.util.Random;

final class AuthenticatedToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private static long idSequence = 0;

    private String Id;

    AuthenticatedToken() {
        Random random = new Random();
        idSequence = idSequence + random.nextInt() % 100000;
        Id = String.valueOf(idSequence);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final AuthenticatedToken that = (AuthenticatedToken) o;

        return Id.equals(that.Id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        return Id.hashCode();
    }
}
