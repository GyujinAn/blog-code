function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms))
}

async function process() {
    console.log('hello');
    await sleep(1000);
    console.log('bye');
    return 3;
}

process().then(
    result => console.log(`result: ${result}`)
);