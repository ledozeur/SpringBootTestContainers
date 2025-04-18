package ru.netology.SpringBootTestContainers;

public class DevProfile implements SystemProfile {
    @Override

    public String getProfile() {
        return "Current profile is dev";
    }
}