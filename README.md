# 📦 Information Management System (資訊管理系統)

這是一個基於 **Spring Boot** 開發的資訊階層管理與後台管理員系統。本系統支援多層次的資訊區塊管理（大項、子項、細項），並提供完整的後台管理員驗證（JWT）、權限控管機制，以及點擊率（Focus Number）的排程統計功能。

---

## 🛠️ 技術棧 (Tech Stack)

- **後端框架**: Java 17+ / Spring Boot
- **資料庫與 ORM**: Spring Data JPA / Hibernate
- **API 文件**: OpenAPI 3 (Swagger UI)
- **安全性**: 自訂 JWT (JSON Web Token) / PBKDF2 密碼雜湊 (加鹽)
- **JSON 處理**: Jackson (`ObjectMapper`, 多型反序列化 `@JsonTypeInfo`)
- **工具庫**: Lombok, Guava

---

## 📂 目錄結構與架構 (Architecture)

本專案採用經典的 Controller-Service-Repository 分層架構，主要分為以下四個 Package：

- `Information_Controller/`: **API 控制層**
  - 負責接收 HTTP Request，整合 Swagger 註解，並將請求轉發給 Service 層。
  - 包含 `Product_Controller` (處理資訊區塊) 與 `Product_Admin_Controller` (處理管理員)。
- `Information_Server/`: **業務邏輯層 (Service)**
  - 處理核心業務邏輯，包含權限檢驗、資料整理與狀態判斷。
  - 包含 `Information_Service` 與 `Information_Admin_Service`。
- `Information_JPA/`: **資料存取層 (Repository & JPA Controller)**
  - 宣告 Spring Data JPA 介面，並包含自訂的 SQL 查詢 (`@Query`)。
  - `Informatin_JPA_Controller` 負責快取、初始化資料以及 `@Scheduled` 定時排程更新。
- `Information_Object/`: **實體與工具物件 (Entity & Utils)**
  - JPA Entities (`Product_Head`, `Product_Kid`, `Product_Tree`, `Product_Admin`)。
  - 加解密與 JWT 處理工具 (`Admin_Lib`)、通用工具 (`Product_Lib`)。

---

## 🚀 核心功能與 API 概覽 (Core Features)

### 1. 階層式資訊管理 (Information Hierarchy)
資訊結構分為三層：`Head` (大項) -> `Kid` (子項) -> `Tree` (細項 JSON 內容)。
API 端點包含：
- **新增/刪除區塊**: `POST /Product_Imformation/setProduct_Information` / `deleteProduct_Information`
- **取得資料**:
  - `GET /Product_Imformation/getProduct_Information` (大項)
  - `GET /Product_Imformation/getProduct_Kid_Information` (子項)
  - `GET /Product_Imformation/getProduct_Tree_Information` (細項)
- **更新狀態與內容**:
  - `POST /Product_Imformation/updateProduct_State` (更新上下架狀態)
  - `POST /Product_Imformation/update_Product_Detail` (更新細項 JSON 內容)
- **點擊數統計**: `GET /Product_Imformation/getNumber_Value` (定時寫入資料庫)

### 2. 管理員與權限系統 (Admin & Security)
- **登入與驗證**: `POST /Product_Admin/adminLogin`
  - 登入成功會返回夾帶 JWT Token 的使用者資訊。
- **管理員 CRUD**:
  - 新增 (`GET /Product_Admin/insert_Admin` 測試用)
  - 更新 (`PUT /api/admin/{id}`)
  - 刪除 (`DELETE /api/admin/{id}`)
- **密碼安全**: 使用 PBKDF2WithHmacSHA256 演算法結合動態 Salt 進行雜湊保護。

---

## ✨ 近期重構亮點 (Refactoring Highlights - 2026/04/24)

本專案近期經歷了一次深度的 AI 重構，大幅提升了程式碼品質與安全性：

1. **語法現代化 (Modern Java Features)**:
   - 廣泛引入 Java 14+ 的 **Switch Expressions** (包含 `->` 與 `yield`)，消除了冗長易錯的 `if-else` 與 `switch-case` 巢狀結構。
   - 使用 Java 16+ 的 **Pattern Matching for `instanceof`** 來簡化轉型並避免 `ClassCastException`。
2. **強化執行緒安全與依賴注入 (DI)**:
   - 透過建構子注入，將 Service 與 Controller 中的所有依賴標記為 `final`，確保物件狀態不可變。
3. **實體層封裝 (Entity Encapsulation)**:
   - 修復了不適當的 `@Component` Spring Bean 註解，讓 `@Entity` 物件回歸 Hibernate 管理其生命週期。
   - 將所有 JPA Entity 欄位從 `public` 修改為 `private`，落實物件導向封裝原則。
4. **清理無效與冗餘程式碼 (Clean Code)**:
   - 清除了多餘的 IDE 自動產生註解 (`// TODO`)、無效的 Imports (如錯誤的 GUI 與舊版日期庫)，統一使用 `java.util.Date` 與 `java.time.LocalDateTime`。
   - 修正了 JWT 產生邏輯中的秒數 Bug (將到期時間從 3.6 秒修正為 1 小時)。

---

## ⚙️ 環境配置與執行 (Setup & Run)

1. **環境要求**:
   - JDK 17 或以上版本
   - Maven 或 Gradle 構建工具
   - 關聯之關聯式資料庫 (如 MySQL / PostgreSQL，請於 `application.properties` 設定)
2. **啟動方式**:
   - 執行 `SpringBootApplication` 主程式啟動伺服器。
   - 預設啟動於 `http://localhost:8080`。
3. **API 測試**:
   - 專案整合了 Swagger，啟動後可訪問 `http://localhost:8080/swagger-ui.html` 來檢視並測試所有 API。

---
*Document updated on April 24, 2026*
