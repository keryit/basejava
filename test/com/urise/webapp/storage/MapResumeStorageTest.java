package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class MapResumeStorageTest extends AbstractStorageTest {

    public MapResumeStorageTest() {
        super(new MapResumeStorage());
    }

    @Test
    public void getResume() {
        Assert.assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test
    public void deleteResume(){
        storage.delete(RESUME_1.getUuid());
        Assert.assertEquals(2, storage.size());
    }
}