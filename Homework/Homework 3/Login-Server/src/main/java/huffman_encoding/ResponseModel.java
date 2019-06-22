package huffman_encoding;

public class ResponseModel {

    public String getEncodingTable() {
        return encodingTable;
    }

    public String getEncodedContent() {
        return encodedContent;
    }

    public void setEncodingTable(String encodingTable) {
        this.encodingTable = encodingTable;
    }

    public void setEncodedContent(String encodedContent) {
        this.encodedContent = encodedContent;
    }

    private String encodingTable;
    private String encodedContent;

    public ResponseModel() {

    }
}
