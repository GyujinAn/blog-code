const { Web3 } = require("web3");

describe("wallet test", () => {
  const web3 = new Web3("http://127.0.0.1:8545");

  // eth_getTransactionCount
  // eth_blockNumber
  // eth_sendRawTransaction

  //   Transaction: 0x95cb780fe3d5f19e2806eaa6c6d7919b4ef71ed1ed3dd82f9ce954105a1afd64
  //   Gas usage: 21000
  //   Block Number: 1
  //   Block Time: Tue Mar 26 2024 15:06:58 GMT+0900 (Korean Standard Time)

  // eth_blockNumber
  // eth_getBlockByNumber
  // eth_getTransactionReceipt
  // eth_getBalance
  // eth_getBalance
  // eth_getBalance
  // eth_getBlockByHash
  // eth_gasPrice

  it("send transaction", async () => {
    // given
    const privateKey = "";
    const fromAddress = "";
    const toAddress = "";
    const oneWei = web3.utils.toWei("1", "wei");
    const value = web3.utils.toHex(oneWei);
    const gasPrice = web3.utils.toHex(0);
    const gasLimit = web3.utils.toHex(21000);
    const txCount = await web3.eth.getTransactionCount(fromAddress);
    const nonce = web3.utils.toHex(txCount);
    const rawTx = {
      nonce: nonce,
      gasPrice: gasPrice,
      gasLimit: gasLimit,
      to: toAddress,
      value: value,
      data: "0x",
    };
    const signedTx = await web3.eth.accounts.signTransaction(rawTx, privateKey);

    // when
    const receipt = await web3.eth.sendSignedTransaction(
      signedTx.rawTransaction
    );

    // then
    expect(fromAddress.toUpperCase()).toBe(receipt.from.toUpperCase());
    expect(toAddress.toUpperCase()).toBe(receipt.to.toUpperCase());
    expect(signedTx.transactionHash.toUpperCase()).toBe(
      receipt.transactionHash.toUpperCase()
    );
  });
});
