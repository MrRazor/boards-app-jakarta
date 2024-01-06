package cz.uhk.boardsappjakarta.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.identitystore.PasswordHash;

@ApplicationScoped
public class BCryptPasswordHash implements PasswordHash {

    @Override
    public String generate(char[] chars) {
        return BCrypt.with(BCrypt.Version.VERSION_2A).hashToString(10, chars);
    }

    @Override
    public boolean verify(char[] chars, String s) {
        return BCrypt.verifyer().verify(chars, s).verified;
    }
}
