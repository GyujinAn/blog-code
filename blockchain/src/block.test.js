const { Web3 } = require("web3");

describe("wallet test", () => {
  const web3 = new Web3("http://127.0.0.1:8545");

  it("get ethereum accounts", async () => {
    // given

    // when
    const blockNumber = await web3.eth.getBlockNumber();
    console.log("block number:", blockNumber);
    const latestBlock = await web3.eth.getBlock();
    await printBlocks(latestBlock);
    // then
  });

  async function printBlocks(block) {
    console.log(block);

    if (
      block.parentHash ===
      "0x0000000000000000000000000000000000000000000000000000000000000000"
    ) {
      return;
    }

    const nextBlock = await web3.eth.getBlock(block.parentHash);
    await printBlocks(nextBlock);
  }
});
