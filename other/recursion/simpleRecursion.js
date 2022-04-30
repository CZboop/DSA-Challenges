const nestedSum = (nestedList, flatList=[]) => {
    for (let i=0; i<nestedList.length; i++){
        if (Array.isArray(nestedList[i])) {
            nestedSum(nestedList[i], flatList);
        } else {
            flatList.push(nestedList[i]);
        }
    }
    return flatList.reduce((x, y) => x + y, 0);
}

console.log(nestedSum([11,3,6,1,[7,[12,7,8],[[[[[[[[15]]]]]]]]]]));

const nthFibonacci = (n) => {
    if (0 < n && n <= 2) {
        return 1;
    } else if (n < 0) {
        return 0;
    } 
    else{
    return nthFibonacci(n - 1) + nthFibonacci(n - 2);
    }
}

console.log(nthFibonacci(20));

const factorialN = (n) => {
    if (n == 1) {
        return 1;
    } else {
        return n * factorialN(n - 1);
    }
}

console.log(factorialN(20));
