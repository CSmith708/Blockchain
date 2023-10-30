package org.example;
import com.google.gson.Gson;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// Define a Block class
class Block {
    private int index;
    private long timestamp;
    private String previousHash;
    private String hash;
    private String data;

    // Constructor
    public Block(int index, String previousHash, String data) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.previousHash = previousHash;
        this.data = data;
        this.hash = calculateHash();
    }

    // Calculate the hash of the block
    public String calculateHash() {
        try {
            int nonce = 12345;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = index + timestamp + previousHash + data + nonce;
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            nonce ++;
            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters
    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public String getData() {
        return data;
    }
}

// Define a Blockchain class
class Blockchain {
    private List<Block> chain;

    // Constructor
    public Blockchain() {
        Gson gson = new Gson();
        chain = new ArrayList<Block>();
        // Create the genesis block (the first block in the chain)
        chain.add(new Block(0, "0", "Genesis Block"));
    }

    // Add a new block to the blockchain
    public void addBlock(String data) {
        Block previousBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(previousBlock.getIndex() + 1, previousBlock.getHash(), data);
        chain.add(newBlock);
    }

    public void printStudentBlockchain() {
        Gson gson = new Gson();
        for (Block block : chain) {
            System.out.println("Block #" + block.getIndex());
            System.out.println("Timestamp: " + block.getTimestamp());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Hash: " + block.getHash());
            if (block.getIndex() == 0){
                System.out.println("Data: " + block.getData());
            } else {
                Student deserialized = gson.fromJson(block.getData(), Student.class);
                System.out.println("Data: " + deserialized.getId() + ", " + deserialized.getName() + ", " + deserialized.getAge() + ", " + deserialized.getCourse());
            }
            System.out.println();
        }
    }
    public void printCreditCardBlockchain() {
        Gson gson = new Gson();
        for (Block block : chain) {
            System.out.println("Block #" + block.getIndex());
            System.out.println("Timestamp: " + block.getTimestamp());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Hash: " + block.getHash());
            if (block.getIndex() == 0){
                System.out.println("Data: " + block.getData());
            } else {
                creditCard deserialized = gson.fromJson(block.getData(), creditCard.class);
                System.out.println("Data: " + deserialized.getCardNumber() + ", " + deserialized.getCustomerName() + ", " + deserialized.getCvc() + ", " + deserialized.getIssuingAuthority() + ", " + deserialized.getTransactionValue());
            }
            System.out.println();
        }
    }

    public void printBankRecordBlockchain() {
        Gson gson = new Gson();
        for (Block block : chain) {
            System.out.println("Block #" + block.getIndex());
            System.out.println("Timestamp: " + block.getTimestamp());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Hash: " + block.getHash());
            if (block.getIndex() == 0){
                System.out.println("Data: " + block.getData());
            } else {
                bankRecord deserialized = gson.fromJson(block.getData(), bankRecord.class);
                System.out.println("Data: " + deserialized.getAccountNumber() + ", " + deserialized.getCustomerName() + ", " + deserialized.getAccountType() + ", " + deserialized.getBankName() + ", " + deserialized.getTransactionValue());
            }
            System.out.println();
        }
    }

}

public class ComplexBlockchain {
    public static void main(String[] args) {
        Gson gson = new Gson();
        // Create Student Blockchain
        Blockchain studentBlockchain = new Blockchain();
        //Add Student Records
        Student student1 = new Student(1234, "Peter Griffin", 19, "IST 242");
        String jsonStudent1 = gson.toJson(student1);
        studentBlockchain.addBlock(jsonStudent1);

        Student student2 = new Student(1235, "Lois Griffin", 19, "IST 242");
        String jsonStudent2 = gson.toJson(student2);
        studentBlockchain.addBlock(jsonStudent2);

        //Create Credit Card Blockchain
        Blockchain creditCardBlockchain = new Blockchain();
        //Add Credit Card Records
        creditCard creditCard1 = new creditCard(4523789435416512l, "Carter Pewterschmidt", 456, "American Express", 1000);
        String jsonCard1 = gson.toJson(creditCard1);
        creditCardBlockchain.addBlock(jsonCard1);

        creditCard creditCard2 = new creditCard(3215456798754562l, "Cleveland Brown", 684, "Discover", 10.99);
        String jsonCard2 = gson.toJson(creditCard2);
        creditCardBlockchain.addBlock(jsonCard2);

        //Create Bank Record Blockchain
        Blockchain bankRecordBlockchain = new Blockchain();
        //Add Bank Record
        bankRecord bankRecord1 = new bankRecord(1234, "Joe Swanson", "Checking", "Wells Fargo", -100);
        String jsonBankRecord1 = gson.toJson(bankRecord1);
        bankRecordBlockchain.addBlock(jsonBankRecord1);

        bankRecord bankRecord2 = new bankRecord(1257, "Glenn Quagmire", "Checking", "Chase", -140);
        String jsonBankRecord2 = gson.toJson(bankRecord2);
        bankRecordBlockchain.addBlock(jsonBankRecord2);

        // Print the blockchain
        System.out.println("Student Blockchain:");
        studentBlockchain.printStudentBlockchain();
        System.out.println("Credit Card Blockchain:");
        creditCardBlockchain.printCreditCardBlockchain();
        System.out.println("Bank Record Blockchain:");
        bankRecordBlockchain.printBankRecordBlockchain();
    }
}