package huffman_encoding;

public class Response {
    private final String encodingTable;
    private final String encodedContent;

    public Response(String content) {
        Compression cps = new Compression();
        this.encodingTable = cps.compress(content,'t');
        this.encodedContent = cps.compress(content,'c');
    }

    public String getEncodingTable() {
        return encodingTable;
    }

    public String getEncodedContent() {
        return encodedContent;
    }
}