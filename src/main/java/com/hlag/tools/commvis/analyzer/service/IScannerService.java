package com.hlag.tools.commvis.analyzer.service;

import com.hlag.tools.commvis.analyzer.annotation.VisualizeHttpsCall;
import com.hlag.tools.commvis.analyzer.model.ISenderReceiverCommunication;

import java.util.Collection;

/**
 * The interface implemented by all services capable to scan for communication receivers and senders.
 */
public interface IScannerService {
    /**
     * Scans the compiled Java classes for senders and receivers.
     *
     * @param rootPackageName the package to start the scan (including all subpackages)
     * @return a Collection of senders and receivers.
     */
    Collection<ISenderReceiverCommunication> scanSenderAndReceiver(String rootPackageName);
}