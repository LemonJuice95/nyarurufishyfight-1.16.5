package io.lemonjuice.nyaruru.entities.api;


import net.minecraft.entity.Entity;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface IHasOwner {
    public abstract Entity getOwner();
    public abstract Optional<UUID> getOwnerUUID();
}
