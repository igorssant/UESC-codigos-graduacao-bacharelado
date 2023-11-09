package org.exemplo.social_networks;

import org.exemplo.iterators.ProfileIterator;

public interface SocialNetwork {

    ProfileIterator createFriendsIterator (String profileEmail);

    ProfileIterator createCoworkersIterator (String profileEmail);
}