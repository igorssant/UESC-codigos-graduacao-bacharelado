package org.exemplo.iterators;

import org.exemplo.profile.Profile;

public interface ProfileIterator {

    boolean hasNext ();

    Profile getNext ();

    void reset ();
}