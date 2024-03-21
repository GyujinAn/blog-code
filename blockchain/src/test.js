const { Web3 } = require("web3");

describe("wallet test", () => {
  const web3 = new Web3("http://127.0.0.1:8545");

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
