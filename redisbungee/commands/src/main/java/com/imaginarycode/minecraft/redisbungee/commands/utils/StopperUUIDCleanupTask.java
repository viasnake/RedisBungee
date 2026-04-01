/*
* Copyright (c) 2026 RedisBungee contributors
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
*
* http://www.eclipse.org/legal/epl-v10.html
*/
package com.imaginarycode.minecraft.redisbungee.commands.utils;

import com.imaginarycode.minecraft.redisbungee.api.RedisBungeePlugin;
import com.imaginarycode.minecraft.redisbungee.api.tasks.UUIDCleanupTask;
import redis.clients.jedis.UnifiedJedis;

public class StopperUUIDCleanupTask extends UUIDCleanupTask {

  public static boolean isRunning = false;

  public StopperUUIDCleanupTask(RedisBungeePlugin<?> plugin) {
    super(plugin);
  }

  @Override
  public Void unifiedJedisTask(UnifiedJedis unifiedJedis) {
    isRunning = true;
    try {
      super.unifiedJedisTask(unifiedJedis);
    } catch (Exception ignored) {
    }
    isRunning = false;
    return null;
  }
}
