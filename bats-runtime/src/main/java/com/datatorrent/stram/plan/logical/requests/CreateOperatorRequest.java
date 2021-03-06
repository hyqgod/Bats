/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.datatorrent.stram.plan.logical.requests;

import org.lealone.bats.api.Operator;

import com.datatorrent.stram.StramUtils;
import com.datatorrent.stram.plan.physical.PlanModifier;

/**
 * <p>CreateOperatorRequest class.</p>
 *
 * @since 0.3.2
 */
public class CreateOperatorRequest extends LogicalPlanRequest
{
  private String operatorName;
  private String operatorFQCN;

  public String getOperatorName()
  {
    return operatorName;
  }

  public void setOperatorName(String operatorName)
  {
    this.operatorName = operatorName;
  }

  public String getOperatorFQCN()
  {
    return operatorFQCN;
  }

  public void setOperatorFQCN(String operatorFQCN)
  {
    this.operatorFQCN = operatorFQCN;
  }

  @Override
  public void execute(PlanModifier pm)
  {
    Class<? extends Operator> operClass = StramUtils.classForName(operatorFQCN, Operator.class);
    Operator operator = StramUtils.newInstance(operClass);
    pm.addOperator(operatorName, operator);
  }

}
