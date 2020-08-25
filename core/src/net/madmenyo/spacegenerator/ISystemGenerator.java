package net.madmenyo.spacegenerator;

public interface ISystemGenerator {
    SpaceBody generateSystem();
    SpaceBody getLastGeneratedSystem();
}
