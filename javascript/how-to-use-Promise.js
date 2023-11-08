function double(number) {
    const promise = new Promise((resolve, reject) => {
      setTimeout(() => {
        if (typeof number !== 'number') {
          reject(new Error('Parameter is not a valid,'))
          return;
        }
        const result = number * 2;
        console.log(`${number} * 2 = ${result}`);
        resolve(result)
      }, 500)
    })
  
    return promise
  }

async function process() {
    let result = 1;
    result = await double(result);
    result = await double(result);
    result = await double(result);
    result = await double(result);
    return result
}



