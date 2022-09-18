package com.sz.blockchain.data;

import com.sz.blockchain.consensus.ProofOfWork;
import com.sz.blockchain.transaction.Transaction;
import com.sz.blockchain.util.CryptoUtils;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

public class Block {

    private int index;

    private String hash;

    private String previousHash;

    private Date currentTime;

    private String data;

    //设定目标难度值
    private int target = ProofOfWork.getTarget();

    //随机数
    private long nonce;

    private List<Transaction> transactions;

    public String txHashes(){
        StringBuffer stringBuffer = new StringBuffer();
        for (Transaction transaction : transactions) {
            stringBuffer.append(transaction.getId());
        }
        return stringBuffer.toString();
    }

    public void setHash(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer = stringBuffer.append(index).append(hash).append(previousHash).append(currentTime).append(data).append(target).append(nonce).append(txHashes());
        this.hash = CryptoUtils.getTwiceSHA256(stringBuffer.toString());
    }

    public String getHash(){
        return hash;
    }


    public Block(int index, String previousHash, Date currentTime, String data, List<Transaction> transactions) {
        this.index = index;
        this.previousHash = previousHash;
        this.currentTime = currentTime;
        this.data = data;
        this.transactions  = transactions;
    }

    public long getTarget(){
        return target;
    }

    public String getBlockInfoWithNonce(long nonce){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(index).append(previousHash).append(currentTime).append(data).append(target).append(nonce).append(txHashes());
        return stringBuffer.toString();
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
