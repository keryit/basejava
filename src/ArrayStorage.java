/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int count;

    void clear() {
        for (int i = 0; i < count; i++) {
            storage[i] = null;
            count = 0;
        }
    }

    void save(Resume resume) {
            storage[count] = resume;
        count++;

    }

    Resume get(String uuid) {
        for (int i =0; i < count; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int deletedIndex = 0;
        for (int i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                count--;
                deletedIndex = i;
            }
        }

        for (int i = deletedIndex; i < count; i++) {
            Resume tmp = storage[i];
            storage[i] = storage[i + 1];
            storage[i + 1] = tmp;

        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] res = new Resume[count];
        for (int i = 0; i < count; i++) {
            res[i] = storage[i];
        }
        return res;
    }

    int size() {

        return count;
    }
}
