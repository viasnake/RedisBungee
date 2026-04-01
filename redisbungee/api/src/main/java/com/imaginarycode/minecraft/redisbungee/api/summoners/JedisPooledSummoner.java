/*
* Copyright (c) 2026 RedisBungee contributors
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
*
* http://www.eclipse.org/legal/epl-v10.html
*/
package com.imaginarycode.minecraft.redisbungee.api.summoners;

import java.io.IOException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.providers.PooledConnectionProvider;

public class JedisPooledSummoner implements Summoner<JedisPooled> {

  private final PooledConnectionProvider connectionProvider;
  private final JedisPool jedisPool;

  public JedisPooledSummoner(PooledConnectionProvider connectionProvider, JedisPool jedisPool) {
    this.connectionProvider = connectionProvider;
    this.jedisPool = jedisPool;
    // test connections
    if (jedisPool != null) {
      try (Jedis jedis = this.jedisPool.getResource()) {
        // Test the connection to make sure configuration is right
        jedis.ping();
      }
    }
    final JedisPooled jedisPooled = this.obtainResource();
    jedisPooled.set("random_data", "0");
    jedisPooled.del("random_data");
  }

  @Override
  public JedisPooled obtainResource() {
    // create UnClosable JedisPool *disposable*
    return new NotClosableJedisPooled(this.connectionProvider);
  }

  public JedisPool getCompatibilityJedisPool() {
    return this.jedisPool;
  }

  @Override
  public void close() throws IOException {
    if (this.jedisPool != null) {
      this.jedisPool.close();
    }
    this.connectionProvider.close();
  }
}
