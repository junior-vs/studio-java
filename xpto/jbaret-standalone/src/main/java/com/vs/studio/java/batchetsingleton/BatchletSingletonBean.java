/*
 * Copyright (c) 2020 Red Hat, Inc. and/or its affiliates.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.vs.studio.java.batchetsingleton;

import jakarta.batch.operations.JobOperator;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;

import java.util.logging.Level;
import java.util.logging.Logger;


@Singleton
public class BatchletSingletonBean {
    static final JobOperator jobOperator = BatchRuntime.getJobOperator();

    static final String JOB_NAME = "batchlets";

    static Logger LOGGER = Logger.getLogger(BatchletSingletonBean.class.getPackage().getName());

    private boolean stop;

    public boolean getStop() {
        return stop = System.currentTimeMillis() % 2 == 0;
    }

    public void setStop(final boolean stop) {
        this.stop = stop;
    }

    @Schedule( second = "0/30", minute = "*", hour = "*", persistent = false)
    private void scheduleJobExecution() {
        final long jobExecutionId = jobOperator.start(JOB_NAME, null);
        LOGGER.log(Level.INFO, "Starting job execution: {0}", jobExecutionId);
    }
}
