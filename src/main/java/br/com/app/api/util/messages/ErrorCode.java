package br.com.app.api.util.messages;

public enum ErrorCode {

    FILE_STORAGE_EMPTY_FILE("error.fundeseg.storage.empty.file", "[Storage] File is empty"),
    FILE_STORAGE_SAVE_FAILED("error.fundeseg.storage.save.file.failed", "[Storage] Attempt to save file failed."),
    FILE_STORAGE_DELETE_FAILED("error.fundeseg.storage.save.file.failed", "[Storage] Attempt to delete file failed."),
    FILE_STORAGE_DELETE_FILE_NOT_EXISTS("error.fundeseg.storage.delete.file.failed", "[Storage] Attempt to delete file failed. File not exists"),
    FILE_STORAGE_GET_FILE_NOT_EXISTS("error.fundeseg.storage.get.file.failed", "[Storage] Attempt to get file failed. File not exists"),
    FILE_STORAGE_PATH_IS_EMPTY("error.fundeseg.storage.path.is.empty", "[Storage] Used storage path is empty"),
    FILE_STORAGE_PATH_NOT_EXISTS("error.fundeseg.storage.path.not.exists", "[Storage] Used storage path not exists"),
    ESTABLISHMENT_ALREADY_EXISTS("error.fundeseg.already.exist","Estabelecimento já cadastrado.");
    

    private String key;
    private String description;

    private ErrorCode(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getCode() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public Message getfundesegError() {
        return new Message(key, description);
    }

    public Message withParameter(String parameterKey, String parameterValue) {
        return getfundesegError().withParameter(parameterKey, parameterValue);
    }
}
