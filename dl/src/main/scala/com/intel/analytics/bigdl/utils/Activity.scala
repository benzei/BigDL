/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intel.analytics.bigdl.utils

import com.intel.analytics.bigdl.tensor.Tensor
import com.intel.analytics.bigdl.tensor.TensorNumericMath.TensorNumeric

import scala.reflect._
import scala.reflect.runtime.universe._

trait Activities {
  def toTensor[T](): Tensor[T] = {
    this.asInstanceOf[Tensor[T]]
  }

  def toTable(): Table = {
    this.asInstanceOf[Table]
  }
}

object Activities {
  def apply[A <: Activities: ClassTag, @specialized(Float, Double) T: ClassTag]()(
    implicit ev: TensorNumeric[T]): Activities = {
    var result: Activities = null

    if (classTag[A] == classTag[Tensor[T]]) {
      result = Tensor[T]()
    } else if (classTag[A] == classTag[Table]) {
      result = T()
    }

    result
  }
}