/**
 * 
 */
package com.june.fastdfs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author junehappylove
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameValuePair {

	protected String name;
	protected String value;

	public NameValuePair(String name) {
		this.name = name;
	}
}
