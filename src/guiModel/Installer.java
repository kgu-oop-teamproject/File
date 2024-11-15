package guiModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 프로그램 전역 정보를 담고있는 프로퍼티 생성 및 받아오기
 * 사용자의 다운로드 폴더에 프로퍼티 생성
 */
public class Installer{
    public Installer() {
        this.globalPropertiesFile = new File(globalPropertiesFolder + globalPropertiesName);

    }

    /**
     * 파일이 존재하는지 확인하고 유효성 검사
     * @return 설치되었으면 true
     */
    private boolean isInstalled() {
        return true;
    }

    /**
     * 설정 파일의 내용이 유효한지 검사.
     * jdk리스트 파일, ui 파일, 프로젝트 리스트 파일
     * @return 모든 파일이 유효하면 true
     */
    private boolean isValid() {
        return false;
    }

    /**
     * 세팅 파일에서 IDE로 값을 가져온다.
     * @return 설정 값이 담긴 Properties
     */
    private Properties getGlobalProperties() {
        File globalProperties = new File(globalPropertiesFolder + globalPropertiesName);

        try {
            FileInputStream settingFileStream = new FileInputStream(globalPropertiesFile);
            Properties properties = new Properties();
            properties.load(settingFileStream);
            settingFileStream.close();
            return properties;
        } catch (FileNotFoundException e) {
            System.out.println("global setting file not found");
            return null;
        } catch (IOException e) {
            System.out.println("global setting file read error");
            return null;
        }
    }



    private final String globalPropertiesFolder = System.getProperty("user.home") + "//Downloads";
    private final String globalPropertiesName = "//OOPTermProject2.properties";
    private File globalPropertiesFile;
    private Properties globalProperties;
}