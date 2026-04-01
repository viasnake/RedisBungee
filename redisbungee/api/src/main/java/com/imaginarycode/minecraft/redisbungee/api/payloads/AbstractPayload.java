/*
* Copyright (c) 2026 RedisBungee contributors
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
*
* http://www.eclipse.org/legal/epl-v10.html
*/
package com.imaginarycode.minecraft.redisbungee.api.payloads;

public abstract class AbstractPayload {

  private final String senderProxy;

  public AbstractPayload(String proxyId) {
    this.senderProxy = proxyId;
  }

  public AbstractPayload(String senderProxy, String className) {
    this.senderProxy = senderProxy;
  }

  public String senderProxy() {
    return senderProxy;
  }

  public String getClassName() {
    return getClass().getName();
  }
}
