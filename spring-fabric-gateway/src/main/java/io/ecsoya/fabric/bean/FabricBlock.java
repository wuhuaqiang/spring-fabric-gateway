package io.ecsoya.fabric.bean;

import org.hyperledger.fabric.sdk.BlockInfo;

import com.google.protobuf.InvalidProtocolBufferException;

import io.ecsoya.fabric.utils.FabricUtil;
import lombok.Data;

@Data
public class FabricBlock {

	private long blockNumber;

	private String channel;

	private int transactionCount;

	private String dataHash;

	private String chaincode;

	/**
	 * Query context transaction id, @see {@link FabricHistory}
	 */
	private String currentTxId;

	/**
	 * Query context transaction timestamp, @see {@link FabricHistory}
	 */
	private String currentTxTimestamp;

	private String currentHash;
	private String previousHash;

	public static FabricBlock create(BlockInfo blockInfo) {
		if (blockInfo == null) {
			return null;
		}
		FabricBlock block = new FabricBlock();
		block.setBlockNumber(blockInfo.getBlockNumber());
		try {
			block.setChannel(blockInfo.getChannelId());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		block.setDataHash(FabricUtil.hashToString(blockInfo.getDataHash()));
		block.setTransactionCount(blockInfo.getTransactionCount());

		block.setPreviousHash(FabricUtil.hashToString(blockInfo.getPreviousHash()));

		return block;
	}
}
