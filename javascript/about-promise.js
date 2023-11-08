// function double(number) {
//     const promise = new Promise((resolve, reject) => {
//       setTimeout(() => {
//         if (typeof number !== 'number') {
//           reject(new Error('Parameter is not a valid,'))
//           return;
//         }
//         const result = number * 2;
//         console.log(`${number} * 2 = ${result}`);
//         resolve(result)
//       }, 500)
//     })
//     return promise
//   }

// async function process() {
//     let result = 1;
//     result = await double(result);
//     result = await double(result);
//     result = await double(result);
//     result = await double(result);
//     return result
// }


// console.log(" === hello world === ")

// setTimeout(() => {
//   console.log("after 3 second")
// }, 3000);

// console.log(" === goodbye world === ")


// const promise = new Promise((resolve, reject) => {
//   // resolve('success')
//   reject(new Error('this is error'))
//   // reject()
//   // throw new Error("throw")
// })

// promise.then(
//   result => {
//     console.log(result)
//   }
// ).catch(
//   e => {
//     console.log("this is catch fucntion")
//     console.log(e)
//   }
// )

function double(number) {
  const promise = new Promise((resolve, reject) => {
    const result = number * 2;
    // console.log(`result: ${result}`)
    resolve(result);
  })
  return promise
}

async function process() {
  let result = 1;
  result = await double(result);
  result = await double(result);
  result = await double(result);
  result = await double(result);
  return result;
}

process().then(
  result => {
    console.log(`result: ${result}`)
  }
)