/*
* Copyright (c) 2026 RedisBungee contributors
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
*
* http://www.eclipse.org/legal/epl-v10.html
*/
package com.imaginarycode.minecraft.redisbungee;

import co.aikar.commands.BungeeCommandIssuer;
import co.aikar.commands.CommandIssuer;
import com.imaginarycode.minecraft.redisbungee.commands.utils.CommandPlatformHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;

public class BungeeCommandPlatformHelper extends CommandPlatformHelper {

  @Override
  public void sendMessage(CommandIssuer issuer, Component component) {
    BungeeCommandIssuer bIssuer = (BungeeCommandIssuer) issuer;
    bIssuer.getIssuer().sendMessage(BungeeComponentSerializer.get().serialize(component));
  }
}
