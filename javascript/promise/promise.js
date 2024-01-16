function double(number, callback){
    setTimeout(() => {
        if (!callback) return;
        const result = number * 2;
        console.log(`${number} * 2 = ${result}`);
        callback(result)
    }, 500)
}

// double(1, result => {
//     double(result, result => {
//         double(result, result => {
//             double(result, result => {
//                 console.log(`last result: ${result}`);
//             })
//         })
//     })
// });

function doubleWithPromise(number) {
    const promise = new Promise((resolve, reject) => {
        setTimeout(()=> {
            if (typeof number !== 'number') {
                reject(new Error('Parameter is not a valid'))
                return;
            }
            const result = number * 2;
            console.log(`${number} * 2 = ${result}`);
            resolve(result)
        }, 500)
    })
    return promise;
}

// doubleWithPromise(1).then(
//     result => {
//         console.log('resolved: ', result)
//     }
// ).catch(
//     e => {
//         console.error(e)
//     }
// )

// doubleWithPromise(null).then(
//     result => {
//         console.log('resolved: ', result)
//     }
// ).catch(
//     e => {
//         console.error(e)
//     }
// )

// doubleWithPromise(1)
//     .then(result => doubleWithPromise(result))
//     .then(result => doubleWithPromise(result))
//     .then(result => doubleWithPromise(result))
//     .then(result => {
//         console.log(`last result: ${result}`);
//     })

doubleWithPromise(1)
    .then(doubleWithPromise)
    .then(doubleWithPromise)
    .then(doubleWithPromise)
    .then(result => {
        console.log(`last result: ${result}`);
    })