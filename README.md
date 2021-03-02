# PrimeAlgorithm
## Hướng dẫn chạy chương trình
Chương trình được build để sẵn ở thư mục PrimeAlgorithm/executable/PrimeAlgorithm.jar.

$ cd PrimeAlgorithm/executable

Có 2 cách để chạy chương trình:
* Cách 1: Kiểm tra một số có phải số nguyên tố hay không?</br>
$ java -jar -DcheckNum=10 PrimeAlgorithm.jar
* Cách 2: Kiểm tra một tập các số nguyên tố trong fileinput và export kết quả ra file output. Mẫu file input có thể tham khảo trong PrimeAlgorithm/executable/input-AKS</br>
$ java -jar -DinputFile=input-AKS -DoutputFile=output-AKS PrimeAlgorithm.jar
