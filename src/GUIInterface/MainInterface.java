package GUIInterface;

public  interface MainInterface {

    interface MainViewInterface {

        /**
         *  텍스트를 인수로 하여 그 텍스트값을 텍스트에디터에 표시하는 메소드
         *  텍스트에 '/n' 포함시 줄 내리기
         * @param text 전달되어 뷰의 텍스트 에디터에 나타날 텍스트
         */
        void showTextEditor(String text);

        /**
         * @return 텍스트 에디터에 있는 값을 반환한다.
         */
        String getTextEditor();

        /**
         * 텍스트를 인수로 하여 그 텍스트 값을 결과화면에 표시하는 메소드
         * @param result 전달되어 뷰의 결과 화면에 표시될 텍스트
         */
        void showResult(String result);

        /**
         * @return 결과 화면에 있는 값을 반환한다.
         */
        String getResult();
    }

    interface MainPresenterInterface {

        void saveButtonClicked();

        void openButtonClicked();

        void clearButtonClicked();

        void compileButtonClicked();

        void deleteButtonClicked();

        void errorSaveButtonClicked();
    }

}