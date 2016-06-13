/*
 * Copyright © 2016 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package swave.core

final case class StreamLimitExceeded(max: Long, offendingElem: Any)
  extends RuntimeException(s"Limit of $max exceeded by element '$offendingElem'")

final class ConfigurationException(msg: String) extends RuntimeException(msg)

final class IllegalAsyncBoundaryException(msg: String) extends RuntimeException(msg)

final class IllegalReuseException(msg: String) extends RuntimeException(msg)

final class SubscriptionTimeoutException(msg: String) extends RuntimeException(msg)