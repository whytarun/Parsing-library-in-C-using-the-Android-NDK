//
// Created by tharun on 11/22/2022.
//

#include <jni.h>
#include <string>
#include <iostream>
#include <regex>
#include <cmath>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_serverlibrary_Server_00024Companion_getSectionName(JNIEnv *env,
                                                                    jobject,
                                                                    jstring sectionIndex/*this*/) {


    std::string ZERO("00");
    std::string ONE("01");
    std::string TWO("10");
    std::string THREE("11");
    std::string SECTION1("SECTION1");
    std::string SECTION2("SECTION2");
    std::string SECTION3("SECTION3");
    std::string SECTION4("SECTION4");
    std::string Mindex = (env)->GetStringUTFChars(sectionIndex, 0);
    if (Mindex.compare(ZERO) == 0) {
        return env->NewStringUTF(SECTION1.c_str());
    } else if (Mindex.compare(ONE) == 0) {
        return env->NewStringUTF(SECTION2.c_str());
    } else if (Mindex.compare(TWO) == 0) {
        return env->NewStringUTF(SECTION3.c_str());
    } else if (Mindex.compare(THREE) == 0) {
        return env->NewStringUTF(SECTION4.c_str());
    }
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_serverlibrary_Server_00024Companion_getCheckMarkResult(JNIEnv *env,
                                                                        jobject,
                                                                        int number/*this*/) {


    std::string UNCHECKED("unchecked");
    std::string CHECKED("checked");

    if (number == 0) {
        return env->NewStringUTF(UNCHECKED.c_str());
    } else if(number==1){
        return env->NewStringUTF(CHECKED.c_str());
    }

}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_serverlibrary_Server_00024Companion_getItemValue(JNIEnv *env,
                                                                  jobject,
                                                                  jlong number/*this*/) {
    long inputNumber = number;
    int decimalNumber = 0;
    int i = 0;
    long remainder;
    std::string ITEM("item");
    while ((int) inputNumber != 0) {
        remainder = inputNumber % 10;
        inputNumber /= 10;
        decimalNumber += (int) (remainder * pow(2.0, (double) i));
        ++i;
    }
    if (decimalNumber >= 0);
    decimalNumber++;
    std::string decimalToString = std::to_string(decimalNumber);
    std::string result = ITEM + decimalToString;
    return env->NewStringUTF(result.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_serverlibrary_Server_00024Companion_convertDecimalToBinary(JNIEnv *env,
                                                                        jobject,
                                                                        jlong number/*this*/) {

    std::string binary = "";
    long res  = number;

    while (res != 0L) {
        binary = std::to_string((res % 2L)) + binary;
        res /= 2;
    }
    size_t n = 8;
    int precision = n - std::min(n, binary.size());
    binary.insert(0, precision, '0');
    std::cout << binary << std::endl;
    return env->NewStringUTF(binary.c_str());
}

