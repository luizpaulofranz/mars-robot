/**
 * Robot positions abstraction.
 */
package com.marsrobot.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {

	private int x;
	private int y;
	private char direction;

}
