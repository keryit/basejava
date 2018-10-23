/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int count = 0;

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null)
                break;
            storage[i] = null;
            count = 0;
        }
    }

    void save(Resume resume) {
        if (storage[count] == null)
            storage[count] = resume;
        count++;

    }

    Resume get(String uuid) {
        for (Resume aStorage : storage) {
            if (aStorage != null && uuid.equals(aStorage.uuid))
                return aStorage;
        }
        return null;
    }

    void delete(String uuid) {
        int deletedIndex = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                count--;
                deletedIndex = i;
            }
        }

        for (int i = deletedIndex; i < storage.length - 1; i++) {
            if (storage[i + 1] == null)
                break;
            Resume tmp = storage[i];
            storage[i] = storage[i + 1];
            storage[i + 1] = tmp;

        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] res = new Resume[size()];
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null)
                break;

            res[i] = storage[i];
        }
        return res;
    }

    int size() {

        int countSize = 0;
        for (Resume aStorage : storage) {
            if (aStorage == null)
                break;

            countSize++;
        }
        return countSize;
    }
}
