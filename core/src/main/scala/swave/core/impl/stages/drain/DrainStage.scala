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

package swave.core.impl.stages.drain

import scala.annotation.compileTimeOnly
import swave.core.{ IllegalAsyncBoundaryException, PipeElem }
import swave.core.impl.{ RunContext, Inport }
import swave.core.impl.stages.Stage

// format: OFF
private[swave] abstract class DrainStage extends Stage { this: PipeElem.Drain =>

  protected final var _inputPipeElem: PipeElem.Basic = PipeElem.Unconnected
  private[this] var _dispatcherId: String = null

  final def inputElem = _inputPipeElem

  protected final def setInputElem(elem: PipeElem.Basic): Unit =
    _inputPipeElem = elem

  final def assignDispatcherId(dispatcherId: String): Unit =
    if ((_dispatcherId eq null) || _dispatcherId.isEmpty) _dispatcherId = dispatcherId
    else if (dispatcherId.nonEmpty && dispatcherId != _dispatcherId)
      throw new IllegalAsyncBoundaryException("Conflicting dispatcher assignment to drain " +
        s"'${getClass.getSimpleName}': [${_dispatcherId}] vs. [$dispatcherId]")

  protected final def registerForRunnerAssignmentIfRequired(ctx: RunContext): Unit =
    if (_dispatcherId ne null) ctx.registerForRunnerAssignment(this, _dispatcherId)

  @compileTimeOnly("Unresolved `connectInAndSealWith` call")
  protected final def connectInAndSealWith(f: (RunContext, Inport) ⇒ State): Unit = ()
}