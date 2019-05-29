/**
 * 
 */
package com.june.fastdfs.socket;

import java.net.InetSocketAddress;

/**
 * @author junehappylove
 *
 */
public interface IBorrowSockectErrorPolicy {

	FdfsSocket handleWhenErrorOccur(FdfsSocketPool pool, InetSocketAddress address, Exception ex);
}
