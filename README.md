# Watlism 🎬

영화와 TV 시리즈 정보를 제공하는 Android 애플리케이션입니다. TMDB(The Movie Database) API를 활용하여 최신 트렌드 콘텐츠부터 인기 영화, 드라마, 애니메이션까지 다양한 영상 콘텐츠 정보를 제공합니다.

<img src="https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=android&logoColor=white" alt="Android">
<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white" alt="Kotlin">
<img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=flat-square&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose">

## ✨ 주요 기능

- **🔥 트렌딩 콘텐츠**: 실시간 인기 영화/TV 시리즈
- **🎥 영화 정보**: 인기 영화 리스트 및 상세 정보
- **📺 시리즈 정보**: 인기 TV 시리즈 및 드라마
- **🎌 애니메이션**: 일본 애니메이션 작품 정보
- **🇰🇷 한국 드라마**: 인기 한국 드라마 콘텐츠
- **📋 상세 정보**: 작품별 평점, 줄거리, 장르, 출시일 등 상세 정보

## 🏗️ 아키텍처

이 프로젝트는 **Clean Architecture**와 **MVVM 패턴**을 기반으로 구성되어 있으며, 다음과 같은 모듈 구조를 가집니다:

### 📁 모듈 구조

```
Watlism/
├── app/                    # 메인 애플리케이션 모듈
├── data/                   # 데이터 레이어 (메인)
│   ├── api/               # TMDB API 통신
│   ├── database/          # Room 데이터베이스
│   ├── datastore/         # DataStore 설정
│   └── system/            # 시스템 관련 데이터소스
├── domain/                # 도메인 레이어 (비즈니스 로직)
└── feature/               # 프레젠테이션 레이어
    ├── core/              # 공통 UI 컴포넌트 및 테마
    ├── home/              # 홈 화면
    └── titledetail/       # 상세 정보 화면
```

### 🎯 Clean Architecture 레이어

#### 📊 Data Layer
데이터 소스별로 모듈을 분리하여 갈아끼기 용이한 구조로 설계했습니다:

- **`data:api`**: TMDB API 통신 담당
  - Retrofit 인터페이스 정의
  - API 응답 DTO 모델
  - API 데이터소스 구현체
- **`data:database`**: 로컬 데이터베이스 관리
  - Room Entity, DAO 정의
  - 데이터베이스 구성
  - 데이터베이스 데이터소스 구현체
- **`data:datastore`**: 사용자 설정 및 캐시 데이터
  - DataStore 설정 관리
  - 설정 데이터소스 구현체
- **`data:system`**: 시스템 레벨 데이터
  - 날짜/시간 등 시스템 정보 제공
- **`data` (메인)**: 데이터 레이어 통합
  - Repository 구현체
  - 데이터소스 인터페이스 정의
  - 도메인 모델로 변환하는 매퍼

#### 🏛️ Domain Layer
순수한 비즈니스 로직만을 포함하는 핵심 레이어:

- **Model**: 비즈니스 도메인 모델 (Title, Movie, Series, Genre 등)
- **Repository Interface**: 데이터 접근을 위한 추상화
- **UseCase**: 비즈니스 로직 캡슐화 (GetDetailUseCase 등)
- **Value Object**: 도메인 특화 값 객체 (TitleId, Rating, Url 등)

#### 🎨 Presentation Layer (Feature)
화면별로 모듈을 분리하여 독립적인 개발이 가능합니다:

- **`feature:core`**: 공통 UI 컴포넌트
  - 테마, 색상, 타이포그래피
  - 공통 로깅 유틸리티
- **`feature:home`**: 홈 화면
  - 트렌딩 콘텐츠 표시
  - 카테고리별 인기 콘텐츠 리스트
  - HomeViewModel, HomeUiState
- **`feature:titledetail`**: 상세 정보 화면
  - 영화/시리즈 상세 정보 표시
  - 평점, 줄거리, 장르 등 상세 데이터
  - TitleDetailViewModel, TitleDetailUiState

#### 🔄 모듈 간 의존성 흐름

```
feature:home ────────┐
                     ├─→ domain ←── data ←─┬─ data:api
feature:titledetail ─┘                     ├─ data:database
                     ↑                     ├─ data:datastore
feature:core ────────┘                     └─ data:system
```

이러한 구조의 장점:
- **유지보수성**: 각 모듈이 독립적으로 개발/테스트 가능
- **확장성**: 새로운 데이터소스나 화면 추가 시 기존 코드 영향 최소화
- **재사용성**: 공통 컴포넌트의 효율적 활용
- **테스트 용이성**: 각 레이어별 단위 테스트 작성 용이

## 🛠️ 기술 스택

### 핵심 기술
- **Kotlin**: 안전하고 간결한 코드 작성
- **Jetpack Compose**: 선언형 UI 프레임워크
- **Coroutines & Flow**: 비동기 프로그래밍 및 반응형 프로그래밍

### Architecture & DI
- **MVVM Pattern**: 관심사 분리와 테스트 용이성
- **Clean Architecture**: 계층 분리와 의존성 규칙
- **Hilt**: 의존성 주입 프레임워크

### 네트워킹 & 데이터
- **Retrofit**: REST API 통신
- **Kotlinx Serialization**: JSON 직렬화/역직렬화
- **OkHttp**: HTTP 클라이언트
- **Room**: 로컬 데이터베이스
- **DataStore**: 설정 데이터 저장

### UI & 이미지
- **Material 3**: 최신 머티리얼 디자인
- **Navigation Compose**: Compose 기반 네비게이션
- **Coil**: 비동기 이미지 로딩

### 날짜 & 시간
- **Kotlinx DateTime**: 크로스플랫폼 날짜/시간 처리

## 📱 지원 환경

- **최소 SDK**: 26 (Android 8.0)
- **타겟 SDK**: 35 (Android 15)
- **컴파일 SDK**: 35

## 🚀 시작하기

### 사전 요구사항

1. Android Studio Ladybug 이상
2. JDK 17
3. TMDB API 키

### 설정

1. **저장소 클론**
   ```bash
   git clone https://github.com/your-username/Watlism.git
   cd Watlism
   ```

2. **API 키 설정**
   
   프로젝트 루트에 `secrets.properties` 파일을 생성하고 TMDB API 키를 추가하세요:
   ```properties
   ACCESS_TOKEN=your_tmdb_access_token_here
   ```

3. **프로젝트 빌드**
   ```bash
   ./gradlew build
   ```

4. **앱 실행**
   
   Android Studio에서 프로젝트를 열고 `Run` 버튼을 클릭하거나:
   ```bash
   ./gradlew installDebug
   ```

## 🔧 주요 라이브러리 버전

| 라이브러리 | 버전 |
|-----------|------|
| Kotlin | 2.1.21 |
| Compose BOM | 2024.09.00 |
| Hilt | 2.56.2 |
| Retrofit | 2.11.0 |
| Room | 2.8.0 |
| Navigation Compose | 2.9.3 |
| Coroutines | 1.10.2 |
| Coil | 2.6.0 |

---

