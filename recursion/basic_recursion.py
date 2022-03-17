# recursive sum list
def add(nested_list, flat_list=[]):
    for c,v in enumerate(nested_list):
        if isinstance(v, list):
            add(v, flat_list)
        else:
            flat_list.append(v)
    return sum(flat_list)

def fibonacci(n):
    if 0<n <=2:
        return 1
    elif n <0:
        return 0
    return fibonacci(n-1) + fibonacci(n-2)

def factorial(n):
    if n == 1:
        return 1
    return n * factorial(n-1)

print(fibonacci(10))

print(factorial(10))

print(add([1,3,6,1,[7,[2,7,8],[[[[[[[[5]]]]]]]]]]))
