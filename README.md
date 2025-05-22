# Prime Numbers Tests

This repository contains implementations of three classic algorithms for prime number detection and factorization:

- **Miller-Rabin**: a probabilistic test to verify primality.
- **Pollard Rho**: an efficient algorithm for integer factorization.
- **Sieve of Eratosthenes**: a classic method to generate all prime numbers up to a given limit.

## Comparison between Miller-Rabin, Pollard’s Rho, and Sieve of Eratosthenes

| Algorithm         | Best Use Case                                |Advantages                                      | Disadvantages                        |
|-------------------|----------------------------------------------|------------------------------------------------|--------------------------------------|
| **Miller-Rabin**  |Testing primality of *large numbers*          |Fast, simple, adjustable accuracy via iterations|Probabilistic                         |
| **Pollard’s Rho** |Factor numbers with *small or close factors*  |Simple, efficient in many cases                 |No guarantee of complete factorization|
|**Sieve of Eratosthenes**|When needing to *list many small primes*|Extremely efficient up to around 10^7           |Requires memory proportional to N     |
---

## When to Use Which?

- **Miller-Rabin**:
  - To check if a number is prime.
  - Ideal for large numbers used in cryptography.
  - Useful when **high confidence** is enough, without absolute guarantee.

- **Pollard’s Rho**:
  - To factor composite numbers.
  - Especially effective when factors are **small or close to each other**.
  - Good as an auxiliary factorization tool (e.g., after detecting a number is composite).

- **Sieve of Eratosthenes**:
  - To generate all prime numbers up to a limit N.
  - Useful for preprocessing or algorithms that rely on prime tables.
  - Highly efficient for **small to medium N** (up to a few millions).

---

## Quick Summary

| Situation                         | Best Algorithm       |
|----------------------------------|---------------------|
| Check if a large number is prime  | **Miller-Rabin**    |
| Factor a composite number          | **Pollard’s Rho**   |
| Generate all primes up to N        | **Sieve of Eratosthenes** |

