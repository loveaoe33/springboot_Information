package Information_Object;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@Schema(description = "帳戶申請資料")
public class Product_Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	@NotBlank
	@Size(min = 4, max = 20)
    @Schema(description = "使用者帳號", example = "loveaoe33")
	public String account;
    @NotBlank
    @Size(min = 4, max = 20)
    @Schema(description = "使用者密碼", example = "55688user123")
	public String password;
    @NotBlank
    @Schema(description = "使用者Token", example = "daksjlkdjl2**qw12ˊ%@!")
	public String token;
    @NotBlank
    @Schema(description = "使用者等級", example = "0")
	public int level;
    @NotBlank
    @Schema(description = "使用者權限單位", example = "MIS")
	public String depart;
    @NotBlank
    @Schema(description = "建立日期", example = "20250401")
    @NotBlank
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	public String create_date;
}
