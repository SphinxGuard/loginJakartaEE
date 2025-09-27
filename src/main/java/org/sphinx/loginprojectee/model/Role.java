package org.sphinx.loginprojectee.model;

public enum Role {

    USER("User"),
    ADMIN("Admin");
    private final String name;

    Role(String name) {
        this.name = name;
    }
    // getter for name
    public String getName() {
        return name;
    }
    public static Role fromRoleName(String name) {
        for (Role r : Role.values()) {
            if (r.name.equalsIgnoreCase(name)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown role name: " + name);
    }

}